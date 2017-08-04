var gulp = require('gulp');

BOOTSTRAP_DIR = './node_modules/bootstrap/dist/';
JQUERY_DIR = './node_modules/jquery/dist/';

var cssFilesToMove = [
    BOOTSTRAP_DIR + 'css/bootstrap.min.css',
    BOOTSTRAP_DIR + 'css/bootstrap.min.css.map'
];

var jsFilesToMove = [
    JQUERY_DIR + 'jquery.min.js',
    JQUERY_DIR + 'jquery.min.map',
    BOOTSTRAP_DIR + 'js/bootstrap.min.js'
];

var fontsToMove = [
    BOOTSTRAP_DIR + 'fonts/*'
];

gulp.task('move-css', function() {
    gulp.src(cssFilesToMove).pipe(gulp.dest('./vendor/css'));
});

gulp.task('move-js', function() {
    gulp.src(jsFilesToMove).pipe(gulp.dest('./vendor/js'));
});

gulp.task('move-fonts', function() {
    gulp.src(fontsToMove).pipe(gulp.dest('./vendor/fonts'));
});

gulp.task('default', ['move-css', 'move-js', 'move-fonts']);