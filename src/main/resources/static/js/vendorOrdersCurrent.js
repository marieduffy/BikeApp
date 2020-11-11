obj = { table: "order_vendors", limit: 20 };
dbParam = JSON.stringify(obj);
xmlhttp = new XMLHttpRequest();
xmlhttp.onreadystatechange = function() {
    let myObj;
    let txt;
    if (this.readyState == 4 && this.status == 200) {
        myObj = JSON.parse(this.responseText);
        txt += "<select>"
        for (let x in myObj) {
            txt += "<option>" + myObj[x].name;
        }
        txt += "</select>"
        document.getElementById("demo").innerHTML = txt;
    }
}
xmlhttp.open("GET", "/vendorOrdersCurrent", true);
xmlhttp.setRequestHeader("Content-type", "application/json");
xmlhttp.send();