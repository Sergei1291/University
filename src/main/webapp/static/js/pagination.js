var rowsOnPage = 5;
var rows;
var th;
var numberPages;

function initialize() {
  rows = document.querySelectorAll(".row");
  th = document.querySelector(".th");
  numberPages = calculateNumberPages();
}

function display(numPage) {
  if (numPage === undefined) {
    numPage = 1;
  }

  let start = calculateStart(numPage);
  let end = calculateEnd(numPage);

  let divTable = document.querySelector(".table");
  divTable.innerHTML = '';

  let table = appendTag(divTable, '', 'table');
  appendTag(table, th.innerHTML, 'tr');

  for (var i = start; i < end; i++) {
    appendTag(table, rows[i].innerHTML, 'tr');
  }
}

function displayPagination() {
  let divPagination = document.querySelector(".pagination");
  divPagination.innerHtml = '';

  for (var i = 1; i <= numberPages; i++) {
    appendTag(divPagination, i, 'a');
  }
}

function eventHandle() {
  let items = document.querySelectorAll(".pagination a");

  for (let item of items) {
    item.addEventListener('click', function() {
    let pageNum = +this.innerHTML;
    display(pageNum);
    })
  }
}

function calculateNumberPages(){
  let numberRows = rows.length;

  return Math.ceil(numberRows / rowsOnPage);
}

function calculateStart(numPage) {

  return rowsOnPage * (numPage - 1);
}

function calculateEnd(numPage) {

  return rowsOnPage * numPage;
}

function appendTag(tagOuter, tagInnerCode, tagInnerName) {
  let tagInner = document.createElement(tagInnerName);
  tagInner.innerHTML = tagInnerCode;
  tagOuter.appendChild(tagInner);

  return tagInner;
}