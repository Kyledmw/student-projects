/*
 * This script contains the core directives
 */
(function (window) {
	var app = angular.module('app.directives', []);

	const DIRECTIVES_DIR = 'directives/';
    const DIALOGS_DIR = 'dialogs/';
    const PUBLIC = 'public/';

	app.directive('initialSelection', function () {
		return {
			restrict: 'E',
			templateUrl: PUBLIC + DIRECTIVES_DIR + DIALOGS_DIR + 'initial-selection.html'
		};
	});

	app.directive('accountPage', function () {
		return {
			restrict: 'E',
			templateUrl: PUBLIC + DIRECTIVES_DIR + DIALOGS_DIR + 'account-page.html'
		};
	});

	app.directive('transactionDialog', function () {
		return {
			restrict: 'E',
			templateUrl: PUBLIC + DIRECTIVES_DIR + DIALOGS_DIR + 'transaction-dialog.html'
		};
	});

})();
