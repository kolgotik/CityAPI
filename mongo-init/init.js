db = db.getSiblingDB('citydb');
db.createCollection('countries');

const fs = require('fs');
const path = '/docker-entrypoint-initdb.d/';
const files = fs.readdirSync(path);

files.forEach(function(file) {
    if (file.endsWith('.json')) {
        print('Inserting file: ' + file);
        const data = fs.readFileSync(path + file, 'utf8');
        const json = JSON.parse(data);
        db.countries.insertOne(json);
    }
});

