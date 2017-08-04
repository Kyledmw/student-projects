var webpack = require('webpack');
var CopyWebpackPlugin = require('copy-webpack-plugin');
var path = require('path');

var BUILD_DIR = path.resolve(__dirname, 'build/public');
var APP_DIR = path.resolve(__dirname, 'src');

var config = {
    entry: APP_DIR + '/App.js',
    output: {
        path: BUILD_DIR,
        filename: 'bundle.js'
    },
    module: {
        loaders: [
            {
                test: /\.js?/,
                include: APP_DIR,
                loader: 'babel'
            },
            {
                test: /\.html$/,
                loader: "html"
            }
        ]
    },
    plugins: [
        new CopyWebpackPlugin([
            {from: APP_DIR + '/index.html'}
        ])
    ]
};

module.exports = config;