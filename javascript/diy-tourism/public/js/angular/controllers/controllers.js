/*
 * This script contains the core controllers
 */
(function (window) {
	var app = angular.module('app.controllers', []);

	app.controller('InitCtrl' , function($scope, $location, TourData, LocationService, TourFactory, DateFactory) {
		
		LocationService.getLocations().then(function (data, status) {
			$scope.locations = data;
			$scope.selectedLocation = $scope.locations[0];
		});
		
		$scope.startDate = DateFactory.getToday();
		$scope.endDate = DateFactory.getNextWeek();
		
		$scope.selectLocation = function(selectedLocation) {
			$scope.selectedLocation = selectedLocation;	
		};
		
		$scope.submit = function() {
			var tour = TourFactory.createTourDateObj($scope.selectedLocation, $scope.startDate, $scope.endDate);
			TourData.tour = tour;
			$location.path('event');
		}
	});
	
	app.controller('LoginCtrl', function($scope, $location, AccountService) {
		 $scope.account = {};
		 $scope.message  = "";
		
		 $scope.login = function() {
			$scope.message = AccountService.login($scope.account.email, $scope.account.password);
			$('#loginModal').modal('hide'); 
			$location.path('checkout');
		 }
		 
		 $scope.register = function() {
			$scope.message = AccountService.register($scope.account.email, $scope.account.password);
			$('#loginModal').modal('hide');
			$location.path('checkout');
		 }
	});
	
	app.controller('CheckoutCtrl', function($scope, $location, AccountService, BasketService, DateFactory, TourData, TransactionService) {
		$scope.userEmail = AccountService.email;
		$scope.events = BasketService.getEvents();
		$scope.cardTypes = ["Visa", "Mastercard"];
		$scope.currentType = $scope.cardTypes[0];
		$scope.card = {};
		$scope.address = {};
		var costAndSaved = BasketService.calculateCostAndSaved();
		$scope.cost = costAndSaved.cost;
		$scope.saved = costAndSaved.saved;
		
		$scope.card.month = DateFactory.getToday();
		$scope.selectType = function(curType) {
			$scope.currentType = curType;
		}
		
		$scope.counties = [
			'Antrim', 
			'Armagh', 
			'Carlow', 
			'Cavan', 
			'Clare', 
			'Cork',
			'Derry', 
			'Donegal', 
			'Down', 
			'Dublin', 
			'Fermanagh', 
			'Galway', 
			'Kerry', 
			'Kildare', 
			'Kilkenny', 
			'Laois', 
			'Leitrim', 
			'Limerick', 
			'Longford', 
			'Louth', 
			'Mayo', 
			'Meath', 
			'Monaghan',
			'Offaly', 
			'Roscommon', 
			'Sligo', 
			'Tipperary', 
			'Tyrone', 
			'Waterford', 
			'Westmeath', 
			'Wexford', 
			'Wicklow'
		]
		
		$scope.countries = [
			'Ireland'
		]
		
		$scope.address.county = $scope.counties[0];
		$scope.address.country = $scope.countries[0];
		
		$scope.selectCounty = function(county) {
			$scope.address.county = county;
		}
		
		$scope.selectCountry = function(country) {
			$scope.address.country = country;
		}
		
		$scope.submit = function () {
			var trans = TransactionService.createTransaction($scope.userEmail, TourData.tour, $scope.events, $scope.address, $scope.cost, $scope.saved);
			TransactionService.submitTransaction(trans);
			$location.path('dashboard');
		}
	});
	
	app.controller('EventCtrl', function($scope, $location, TourData, LocationService, BasketService, AccountService) {
		
		$scope.initialTicket = true;
		$scope.curPercentOff = 0.05;
		$scope.savings = [
			
		]
		
		$scope.cost = 0;

		LocationService.getEventsForTour(TourData.tour).then(function(data, status) {
			$scope.nearbyEvents = data;
		});
		
		$scope.selectedEvents = [];
		
		$scope.add = function(ticket) {	
			if($scope.selectedEvents.indexOf(ticket) == -1) {
				ticket.quantity = 1;
				if($scope.initialTicket) {
					$scope.savings.push({event : ticket.event,
					percent: 0});
					$scope.initialTicket = false;
					$scope.cost = $scope.cost  + BasketService.calcCostOfTicket(ticket, 0);
				} else {
					$scope.savings.push({event : ticket.event,
					percent: $scope.curPercentOff});
					$scope.cost = $scope.cost  + BasketService.calcCostOfTicket(ticket, $scope.curPercentOff);
				}
				$scope.selectedEvents.push(ticket);
			} else {
				$scope.selectedEvents.forEach(function(cur) {
					if(cur.id == ticket.id) {
						$scope.savings.push({event : ticket.event,
				percent: $scope.curPercentOff});
						cur.quantity = cur.quantity + 1;
						$scope.cost = $scope.cost + BasketService.calcCostOfTicket(ticket, $scope.curPercentOff);
						return;
					}
				});
			}
		}
		
		$scope.submit = function() {
			if(!AccountService.isLoggedIn()) {	
				$('#loginModal').modal('show');
			} else {
				$location.path('checkout');
			}
			BasketService.setEvents($scope.selectedEvents, $scope.savings);
		}
		
		$scope.remove = function(ticket) {
			var count = 0;
			$scope.selectedEvents.forEach(function(cur) {
				if(cur.id == ticket.id) {
					cur.quantity = cur.quantity - 1;
					if(cur.quantity == 0) {
						$scope.selectedEvents.splice(count, 1);
					}

					if($scope.selectedEvents[0] == null) {
						$scope.initialTicket = true;
						$scope.cost = $scope.cost - BasketService.calcCostOfTicket(ticket, 0);
					} else {
						$scope.cost = $scope.cost - BasketService.calcCostOfTicket(ticket, $scope.curPercentOff);
					}

					return;
				}

				count = count + 1;
			});
		}
	});
	
	app.controller('DashboardCtrl', function($scope, TransactionService) {
		$scope.transactions = TransactionService.getTransactions();

		$scope.transactionSelect = function(transaction) {
			$('#transactionModal' + transaction.id).modal('show');
		}

		$scope.formattedDate = function (date) {
   			 var d = new Date(date || Date.now()),
	        	month = '' + (d.getMonth() + 1),
	        	day = '' + d.getDate(),
	        	year = d.getFullYear();

	    	if (month.length < 2) month = '0' + month;
	    	if (day.length < 2) day = '0' + day;

	    	return [month, day, year].join('/');
	}
	});
})();