var express = require('express');
var router = express.Router();
var database = require('../lib/mongo.js');

/* GET home page. */
router.get('/', function(req, res) {
  res.render('index', {});
});

/* POST Add User */
router.post('/users', function(req, res) {
  console.log("Checkin from: " + req.param('name'));
  console.log("Email: " + req.param('email'));
  var user = {
    name: req.param('name'),
    email: req.param('email')
  };
  database.addUser(user, function(doc) {
    res.end(doc ? "OK" : "ERROR");
  });
});

/* GET list. */
router.get('/list', function(req, res) {
  database.getUsers(function(users) {
    res.render('list', { users: users, total: users.length });
  });
});

module.exports = router;
