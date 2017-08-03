
from google.appengine.ext import ndb

class BillingAddress(ndb.Model):
	
class Transaction(ndb.Model):
	
	
class Event(ndb.Model):
    id = ndb.IntegerProperty(required=True)
    event = ndb.StringProperty(required=True)
	price = ndb.FloatProperty(required=True)
	lat = ndb.FloatProperty(required=True)
	lng = ndb.FloatProperty(required=True)

class TicketPurchase(ndb.Model):
	id = ndb.IntegerProperty(required=True);
	event_id = ndb.IntegerProperty(required=True);
	
class Tour(ndb.Model):
	
class Account(ndb.Model):

class Location(ndb.Model):

	