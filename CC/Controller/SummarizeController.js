const Summarize = require("../Model/SummarizeModel");
const Sequelize = require('sequelize');
const uuid = require('uuid');

const addSummarize =  async(req, res, next) => {
    try {
        const userID = uuid.v4();
        const createdDate = new Date().toISOString();
        const {
            username,
            caption,
            category,
            // location,
            // summarize,
            // typeSummarize,
            // createdBy
        } = req.body

        const newSummarize = new Summarize ({
            userID,
            username,
            caption,
            category,
            createdDate,
            // location,
            // summarize,
            // typeSummarize,
            // createdBy
        })

        await newSummarize.save();
        res.json({
            status:'true',
            message: 'success', 
            newSummarize
        });
    } catch (err) {
        console.error(err.message);
        res.status(500).json({
            status:'false',
            message: 'Internal Server Error'
        });
    }
}

const getAllSummarize = async(req, res, next) => {
    try {
        const getAllSummarize = await Summarize.findAll({});

        res.json({
            status:'true',
            message: 'success', 
            getAllSummarize
        });
    } catch (err) {
        console.error(err.message);
        res.status(500).json({
            status:'false',
            message: 'Internal Server Error'
        });
    }
}

const getSummarizeByID =  async(req, res, next) => {
    try {
        const id = req.params.id;

        const getSummarize = await Summarize.findOne({
            where: { userID:id }
        });

        if (id !== undefined) {
            res.json({
                status: 'true',
                message: 'success',
                getSummarize
            });
        }

        else {
            res.status(404).json({
                status: 'false',
                message: "Summarize doesn't found"
            });
        }
    } catch (err) {
        console.error(err.message);
        res.status(500).json({
            status:'false',
            message: 'Internal Server Error'
        });
    }
}

const getSummarizeByCategory =  async(req, res, next) => {
    try {
        const category = req.params.category;

        const getSummarize = await Summarize.findAll({
            where: { category:category }
        });

        if (category !== undefined) {
            res.json({
                status: 'true',
                message: 'success',
                getSummarize
            });
        }
        else {
            res.status(404).json({
                status: 'false',
                message: "Summarize doesn't found"
            });
        }
    } catch (err) {
        console.error(err.message);
        res.status(500).json({
            status:'false',
            message: 'Internal Server Error'
        });
    }
}

const getSummarizeByLocation =  async(req, res, next) => {
    try {
        const category = req.params.category;
        const location = req.params.location;

        const getSummarize = await Summarize.findAll({
            where: { category:category, location:location }
        });

        if (category !== undefined) {
            res.json({
                status: 'true',
                message: 'success',
                getSummarize
            });
        }
        else {
            res.status(404).json({
                status: 'false',
                message: "Summarize doesn't found"
            });
        }
    } catch (err) {
        console.error(err.message);
        res.status(500).json({
            status:'false',
            message: 'Internal Server Error'
        });
    }
}

const getAllSummarizeList =  async(req, res, next) => {
    try {
        const allSummarize = [];
        const pendidikan = {};
        const sosial = {};
        const kesehatan = {};
        const infrastruktur = {};

        const getCategoryPendidikan = await Summarize.findAll({
            where: { category: 'pendidikan' }
        });
        pendidikan.category = 'pendidikan';
        pendidikan.SummarizeByCategory = getCategoryPendidikan;
        allSummarize.push(pendidikan);

        const getCategorySosial = await Summarize.findAll({
            where: { category: 'sosial' }
        });
        sosial.category = 'sosial';
        sosial.SummarizeByCategory = getCategorySosial;
        allSummarize.push(sosial);

        const getCategoryKesehatan = await Summarize.findAll({
            where: { category: 'kesehatan' }
        });
        kesehatan.category = 'kesehatan';
        kesehatan.SummarizeByCategory = getCategoryKesehatan;
        allSummarize.push(kesehatan);

        const getCategoryInfrastruktur = await Summarize.findAll({
            where: { category: 'infrastruktur' }
        });
        infrastruktur.category = 'infrastruktur';
        infrastruktur.SummarizeByCategory = getCategoryInfrastruktur;
        allSummarize.push(infrastruktur);

        res.json({
            status: 'true',
            message: 'success',
            AllSummarize: allSummarize
        });
    } catch (err) {
        console.error(err.message);
        res.status(500).json({
            status:'false',
            message: 'Internal Server Error'
        });
    }
}

const getSummarizeByLocationList =  async(req, res, next) => {
    try {
        const category = req.params.category;
        const location = req.params.location;

        const getSummarizeCategory = await Summarize.findOne({
            where: { category:category, location:location },
            attributes: ['category', 'location']
        });

        const getSummarize = await Summarize.findAll({
            where: { category:category, location:location },
            attributes: ['userID', 'username', 'caption']
        });

        const getCategory = getSummarizeCategory.category;
        const getLocation = getSummarizeCategory.location;

        if (category !== undefined) {
            res.json({
                status: 'true',
                message: 'success',
                category: getCategory,
                location: getLocation,
                getSummarize
            });
        }
        else {
            res.status(404).json({
                status: 'false',
                message: "Summarize doesn't found"
            });
        }
    } catch (err) {
        console.error(err.message);
        res.status(500).json({
            status:'false',
            message: 'Internal Server Error'
        });
    }
}

module.exports = {
    addSummarize,
    getAllSummarize,
    getSummarizeByID,
    getSummarizeByCategory,
    getSummarizeByLocation,
    getSummarizeByLocationList,
    getAllSummarizeList
}