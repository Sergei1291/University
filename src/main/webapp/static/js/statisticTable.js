var rootDiv;
var rows;
var th;
var rowsInTable = 20;

function initialize(root) {
  rootDiv=root;
  rows = document.querySelectorAll(".row");
  th = document.querySelector(".th");
}

function display() {
  let divTable = document.querySelector("." + rootDiv);
  divTable.innerHTML = '';

  let table = appendTag(divTable, '', 'table');
  appendTag(table, th.innerHTML + th.innerHTML, 'tr');

  for (var i = 0; i < rowsInTable; i++) {
    appendTag(table, rows[i].innerHTML + rows[i+rowsInTable].innerHTML, 'tr');
  }
}

function appendTag(tagOuter, tagInnerCode, tagInnerName) {
  let tagInner = document.createElement(tagInnerName);
  tagInner.innerHTML = tagInnerCode;
  tagOuter.appendChild(tagInner);

  return tagInner;
}