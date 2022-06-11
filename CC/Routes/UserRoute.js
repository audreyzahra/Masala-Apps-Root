const UserController = require('../Controller/UserController');
const router = require('express').Router();

// Routes untuk User
router.post('/register', UserController.register);
router.post('/login', UserController.login);

module.exports = router;