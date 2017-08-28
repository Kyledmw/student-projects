var webpack = require('webpack');
var CopyWebpackPlugin = require('copy-webpack-plugin');
var path = require('path');

var BUILD_DIR = path.resolve(__dirname, 'build');
var APP_DIR = path.resolve(__dirname, 'src');
var ASSETS_DIR = path.resolve(__dirname, 'assets');

var config = {
    entry: APP_DIR + '/index.jsx',
    output: {
        path: BUILD_DIR,
        filename: 'bundle.js'
    },
    module: {
        loaders: [
            {
                test: /\.jsx?/,
                include: APP_DIR,
                loader: 'babel'
            }
        ]
    },
    resolve: {
        extensions: ['', '.js', '.jsx', '.json']
    },
    plugins: [
        new CopyWebpackPlugin([
            {from: APP_DIR + '/index.html'},
            {from: ASSETS_DIR + '/**/*'}
        ])
    ]
};

module.exports = config;
