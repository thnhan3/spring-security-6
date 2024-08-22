const express = require('express');
const path = require('path');

const app = express();

// Set up static file server
app.use(express.static(path.join(__dirname, 'public')));

// Set public is view  directory

app.set('views', path.join(__dirname, 'public'));


// render html files
app.engine('html', require('ejs').renderFile);
app.set('view engine', 'html');


app.get('/', (req, res) => {
  res.render('index'); 
});

app.get("/resource", (req, res) => {
  res.render("resource.html");
});

app.listen(3000, () => {
  console.log("Server is running on http://localhost:3000");
});