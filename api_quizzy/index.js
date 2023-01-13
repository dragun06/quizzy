const express = require('express');
const app = express();

const cm1Geo = require('./ressources/coursesCM1Geo.json');
const cm1Hist= require('./ressources/coursesCM1Hist.json');
const cm2Geo= require('./ressources/coursesCM2Geo.json');
const cm2Hist= require('./ressources/coursesCM2Hist.json');

app.get('/CM1Geo', (req,res) => {res.status(200).json(cm1Geo)});
app.get('/CM1Hist', (req,res) => {res.status(200).json(cm1Hist)});
app.get('/CM2Geo', (req,res) => {res.status(200).json(cm2Geo)});
app.get('/CM2Hist', (req,res) => {res.status(200).json(cm2Hist)});
app.listen(8080, () => {console.log("Serveur à l'écoute sur le port 8080")});