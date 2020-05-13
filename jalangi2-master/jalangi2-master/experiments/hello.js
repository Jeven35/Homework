var fs = require("fs");
var hello = "var";

console.log(hello);

setTimeout(function() {
  hello = "hello";
  console.log(hello);
},1000);

fs.writeFileSync("test.txt","I am test!");

var person ={
  name: "lisi",
  age: 21,
  family: ["lida","lier","wangwu"],
  say: function(){
      alert(this.name);
  }
};
person.name = "RJW";
console.log(person.name);
console.log(person.age);