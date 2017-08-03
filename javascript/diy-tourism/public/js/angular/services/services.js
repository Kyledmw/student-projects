/**
 * This script contains the core services
 */
(function(window) {
    var app = angular.module('app.services', []);
	
	 app.factory('HTTPService', function($http) {
		 
		 const GET = 'GET';
		 const POST = 'POST';
		 
		 function getRequest(inputUrl, callback) {     
			$http({method: GET, url: inputUrl, cache: true}).
				success(function(data, status) {
					callback(data, status);
				}).error(function(data, status) {
					callback(data, status);
				});
		 }
		 
		 function postRequest(inputUrl, data, callback) {
			 $http.post(inputUrl, data). 
			 	error(function(data, status) {
					 callback(data, status);
				 }). success(function(data, status){
					 callback(data, status);
				 });
		 }
		 
		 this.getRequest = getRequest;
		 this.postRequest = postRequest;
		 
		 return this;
	 });
	 
	 app.factory('DateFactory', function() {
		 function getCurYear() {
			 return new Date().getFullYear();
		 }
		 
		 function getToday() {
			 return new Date();
		 }
		 
		 function getNextWeek() {
			return new Date(new Date().getTime() + 7 * 24 * 60 * 60 * 1000);
		 }
		 
		 this.getCurYear = getCurYear;
		 this.getToday = getToday;
		 this.getNextWeek = getNextWeek;
		 
		 return this;
	 });
	 
	 app.factory('TourData', function() {
		  this.tour = null;
		  
		  return this;
	 });
	 
	 app.factory('LocationService', function($q) {
		function getLocations() {
            var deffered = $q.defer();
			
			var data = [
				{
					id : 1,
					lat : 51.8982897,
					long: -8.4809582,
					fullAddress : 'Centre, Cork, Ireland',
					title : 'Cork City Centre'
				}
			];
			
            deffered.resolve(data, "success");
            
            return deffered.promise;
		} 
		
		function getEventsForTour(tour) {	
            var deffered = $q.defer();
					
			var data = [
				{
					'id' : 1,
					'event': 'Cork Prison',
					'price': 5,
					'lat' : 51.9098881,
					'long': -8.4610287
				},
				{
					'id' : 2,
					'event': 'Fota Wildlife',
					'price': 10,
					'lat' : 51.889922,
					'long': -8.309187
				},
				{
					'id' : 3,
					'event': 'Spike Island Tour',
					'price': 5,
					'lat' : 51.8340188,
					'long': -8.2857619
				}
			];
			
            deffered.resolve(data, "success");
            
            return deffered.promise;
			
		}
		
		this.getLocations = getLocations;
		this.getEventsForTour = getEventsForTour;
		
		return this;
	 });
	 
	 app.factory('TourFactory', function() {
		 function createTourDateObj(location, startDate, endDate) {
			 return {
				 'loc' : location,
				 'start' : startDate,
				 'end' : endDate
			 }
		 }
		 
		 this.createTourDateObj = createTourDateObj;
		 return this;
	 });
	 
	 app.factory('AccountService', function() {
		 var self = this;
		 var loggedIn = false;
		 
		 function login(email, password) {
			 this.email = email;
			 loggedIn = true;
			 return "Login Success";
		 }
		 
		 function signup(email, password) {
			 this.email = email;
			 loggedIn = true;
			 return "Registered Successfully"
		 }
		 
		 function isLoggedIn() {
			 return loggedIn;
		 }
		 
		 this.signup = signup;
		 this.login = login; 
		 this.isLoggedIn = isLoggedIn;
		 
		 return this;
	 });
	 
	 app.factory('BasketService', function() {
		var selectedEvents = [];
		var savings = [];
		
		function setEvents(events, sav) {
			selectedEvents = events;
			savings  = sav;
		}
		
		function getEvents() {
			return selectedEvents;
		}
		
		function calculateCostAndSaved() {
			var cost = 0;
			var total = 0;
			var saved = 0;
			savings.forEach(function(cur) {
				for(var i = 0; i< selectedEvents.length; i++) {
					if(cur.event == selectedEvents[i].event) {
						var amountOff = (selectedEvents[i].price * cur.percent);
						saved = saved + amountOff;
						total = total + selectedEvents[i].price;
					}
				}
			});
			cost = total - saved;
			
			return { cost: cost, 
					saved: saved }
		}

		function calcCostOfTicket(event, savings) {
			var amountOff = (event.price * savings);
			return (event.price - amountOff);
		}
		
		this.getEvents = getEvents;
		this.setEvents = setEvents;
		this.calculateCostAndSaved = calculateCostAndSaved;
		this.calcCostOfTicket = calcCostOfTicket;
		
		return this;
	 });
	 
	 app.factory('TransactionService', function() {
		 var transactions = [];
		 var nextId = 1;
		 
		 function createTransaction(userEmail, tour, events, address, price, saved) {
			 var trans =  {
				 id: nextId,
				 user: userEmail,
				 tour: tour,
				 events: events,
				 address: address,
				 price: price,
				 saved: saved
			 }
			 nextId = nextId + 1;
			 return trans;
			 
		 }
		 
		 function submitTransaction(transaction) {
			 transactions.push(transaction);
		 }
		 
		 function getTransactions() {
			 return transactions;
		 }
		 
		 this.createTransaction = createTransaction;
		 this.submitTransaction = submitTransaction;
		 this.getTransactions = getTransactions;
		 
		 return this;
	 });
})();

    