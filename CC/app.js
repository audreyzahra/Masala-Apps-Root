const express = require("express");
const cors = require("cors");
const app = express();
const bodyParser = require('body-parser');
const db = require("./config/db");
const swaggerUi = require('swagger-ui-express');
swaggerDocument = require('./swagger.json');

const summarizeRouter = require('./Routes/SummarizeRoute');
const userRouter = require('./Routes/UserRoute');

db.authenticate().then(() => 
    console.log("Connected to Database."
)).catch (err => {
    console.error('Unable to connect to the database:', err);
});

app.use(express.urlencoded({extended: true}));
app.use(bodyParser.json());
app.use((req, res, next) => {
    res.setHeader('Access-Control-Allow-Origin', '*');
    res.setHeader('Access-Control-Allow-Methods', 'OPTIONS, GET, POST, PUT, PATCH, DELETE');
    res.setHeader('Access-Control-Allow-Headers', 'Origin, X-Requested-With, Accept, Content-Type, Authorization');
    next();
});

app.use(cors());

app.use('/api-docs', swaggerUi.serve, swaggerUi.setup(swaggerDocument));

app.use('/summarize', summarizeRouter);
app.use('/user', userRouter);

app.listen(8000, () => console.log("port berjalan di 8000"));