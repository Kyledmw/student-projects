from google.appengine.ext import ndb


class AboutMe(ndb.Model):
    text = ndb.StringProperty(required=True)
    
class ProfilePicture(ndb.Model):
    image = ndb.StringProperty(required=True)
    blob_key = ndb.BlobKeyProperty(required=True)
    
class Skill(ndb.Model):
    id = ndb.IntegerProperty(required=True)
    name =  ndb.StringProperty(required=True)
    icon =  ndb.StringProperty(required=True)
    desc =  ndb.StringProperty(required=True)
    project =  ndb.StringProperty(required=True)
    github =  ndb.StringProperty(required=True)
    blob_key = ndb.BlobKeyProperty(required=True)
    
class Experience(ndb.Model):
    id = ndb.IntegerProperty(required=True)
    title = ndb.StringProperty(required=True)
    desc = ndb.StringProperty(required=True)
    image = ndb.StringProperty(required=True)
    width = ndb.StringProperty(required=True)
    height = ndb.StringProperty(required=True)
    href = ndb.StringProperty(required=True)
    blob_key = ndb.BlobKeyProperty(required=True)
    
class Message(ndb.Model):
    id = ndb.IntegerProperty(required=True)
    full_name = ndb.StringProperty(required=True)
    email = ndb.StringProperty(required=True)
    content = ndb.StringProperty(required=True)

class MindMessage(ndb.Model):
    id = ndb.IntegerProperty(required=True)
    name = ndb.StringProperty(required=True);
    email = ndb.StringProperty(required=True)
    product = ndb.StringProperty(required=True);
    price = ndb.IntegerProperty(required=True);
    content = ndb.StringProperty(required=True);
    
class IDCounter(ndb.Model):
    current_highest_skill = ndb.IntegerProperty(required=True)
    current_highest_experience = ndb.IntegerProperty(required=True)
    current_highest_message = ndb.IntegerProperty(required=True)
    current_highest_mind_message = ndb.IntegerProperty(required=True)
    

class SingletonsController():
    
    def getIDCounter(self):
        query = IDCounter.query()
        data = query.fetch()
        data.append(None)
        return data[0]
    
    def getAboutMe(self):
        query = AboutMe.query()
        data = query.fetch()
        data.append(None)
        return data[0]
    
    def getProfilePicture(self):
        query = ProfilePicture.query()
        data = query.fetch()
        data.append(None)
        return data[0]