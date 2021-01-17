package com.epam.university.connection;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.ReentrantLock;

public class ConnectionPool {

    private static final int CONNECTIONS_NUMBER = 5;

    private static final AtomicBoolean isInstanceCreated = new AtomicBoolean(false);
    private static final ReentrantLock LOCK_SINGLETON = new ReentrantLock();
    private static final ReentrantLock LOCK_CONNECTIONS = new ReentrantLock();

    private static ConnectionPool instance;

    private final ConnectionFactory connectionFactory = new ConnectionFactory();
    private Queue<ProxyConnection> availableConnections;
    private List<ProxyConnection> usedConnections;
    private Semaphore semaphore;

    private ConnectionPool() {
    }

    public static ConnectionPool getInstance() {
        if (!isInstanceCreated.get()) {
            LOCK_SINGLETON.lock();
            try {
                ConnectionPool localInstance = instance;
                if (localInstance == null) {
                    localInstance = new ConnectionPool();
                    localInstance.initialize();
                    instance = localInstance;
                    isInstanceCreated.set(true);
                }
            } finally {
                LOCK_SINGLETON.unlock();
            }
        }
        return instance;
    }

    private void initialize() {
        semaphore = new Semaphore(CONNECTIONS_NUMBER, true);
        this.availableConnections = new LinkedList<>();
        this.usedConnections = new ArrayList<>();
        initializeConnections();
    }

    private void initializeConnections() {
        for (int i = 0; i < CONNECTIONS_NUMBER; i++) {
            Connection connection = connectionFactory.create();
            ProxyConnection proxyConnection = new ProxyConnection(connection, this);
            availableConnections.offer(proxyConnection);
            usedConnections.add(proxyConnection);
        }
    }

    public ProxyConnection getConnection() {
        try {
            semaphore.acquire();
            ProxyConnection freeConnection;
            LOCK_CONNECTIONS.lock();
            try {
                freeConnection = availableConnections.poll();
            } finally {
                LOCK_CONNECTIONS.unlock();
            }
            return freeConnection;
        } catch (InterruptedException e) {
            throw new ConnectionException(e.getMessage(), e);
        }
    }

    public void returnConnection(ProxyConnection proxyConnection) {
        LOCK_CONNECTIONS.lock();
        try {
            if (usedConnections.contains(proxyConnection)) {
                availableConnections.offer(proxyConnection);
            }
        } finally {
            LOCK_CONNECTIONS.unlock();
        }
        semaphore.release();
    }

    public void shutdown() {
        try {
            for (ProxyConnection proxyConnection : usedConnections) {
                Connection connection = proxyConnection.getConnection();
                connection.close();
            }
        } catch (SQLException e) {
            throw new ConnectionException(e.getMessage(), e);
        }
    }

}