var webpack = require('webpack');
var webpackDevServer = require('webpack-dev-server');
var config = require('./webpack.hot.config');

new webpackDevServer(webpack(config), {
    publicPath: config.output.publicPath,
    hot: true,
    headers: { 'Access-Control-Allow-Origin': '*' },
    historyApiFallback: true
}).listen(3000, '0.0.0.0', function (err, result) {
        if (err) {
            return console.log(err);
        }

        console.log('Listening at http://localhost:3000/');
    });