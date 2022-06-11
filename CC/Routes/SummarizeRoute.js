const SummarizeController = require('../Controller/SummarizeController');
const router = require('express').Router();
const auth = require('../middleware/auth');

// Routes untuk Summarize
router.post('/addSummarize', auth, SummarizeController.addSummarize);
router.get('/', auth, SummarizeController.getAllSummarize);
router.get('/getSummarizeList', auth, SummarizeController.getAllSummarizeList);
// router.get('/getSummarize/:category', auth, SummarizeController.getSummarizeByCategory);
// router.get('/getSummarize/:category/:location', auth, SummarizeController.getSummarizeByLocation);
// router.get('/getSummarizeList/:category/:location', auth, SummarizeController.getSummarizeByLocationList);
// router.get('/getSummarize/:id', auth, SummarizeController.getSummarizeByID);


module.exports = router;