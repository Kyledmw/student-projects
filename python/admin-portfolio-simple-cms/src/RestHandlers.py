import ast
import json
import logging
import re

import webapp2

from UtilMethods import get_aboutme_profilepic_dict, get_experience_dict_list, \
    get_skills_dict_list, get_id_counter
from model import Message, MindMessage


class RestHandler(webapp2.RequestHandler):
    
    def dispatch(self):
        super(RestHandler, self).dispatch()
        
    def SendJson(self, r):
        self.response.headers.add_header('Access-Control-Allow-Origin', '*')
        self.response.write(json.dumps(r))    
        
    def options(self):      
        self.response.headers['Access-Control-Allow-Origin'] = '*'
        self.response.headers['Access-Control-Allow-Headers'] = 'Origin, X-Requested-With, Content-Type, Accept'
        self.response.headers['Access-Control-Allow-Methods'] = 'POST, GET, PUT, DELETE'
        
class AboutMeHandler(RestHandler):
    
    def get(self):
        self.SendJson(get_aboutme_profilepic_dict())

class SkillHandler(RestHandler):
    
    def get(self):
        self.SendJson(get_skills_dict_list())
        
class ExperienceHandler(RestHandler):
    
    def get(self):
        self.SendJson(get_experience_dict_list())
        
        
class MessageHandler(RestHandler):
    
    def post(self):
        data = ast.literal_eval(self.request.body)
        logging.info("Received Message")
        email_regex = re.compile("[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?");
        
        sent_name = data.get('name')
        sent_email = data.get('email')
        sent_content = data.get('content')
        
        if( not email_regex.match(sent_email)
           or sent_name == "" 
           or sent_content == ""): 
            self.response.headers.add_header('Access-Control-Allow-Origin', '*')
            logging.info("Invalid Email")
            return False
        
        id_counter = get_id_counter()
        if id_counter.current_highest_message == None:
            id_counter.current_highest_message = 0
            
        curID = id_counter.current_highest_message
        curID = curID + 1
        
        message = Message(id=curID, full_name=sent_name, email=sent_email, content=sent_content)
        
        message.put()
        
        id_counter.current_highest_message = curID
        id_counter.put()
        
        self.response.headers.add_header('Access-Control-Allow-Origin', '*')
        return True

class MindsMessageHandler(RestHandler):

    def post(self):
        data = ast.literal_eval(self.request.body)
        sent_name = data.get('name')
        sent_email = data.get('email')
        sent_product = data.get('product')
        sent_price = data.get('price')
        sent_content = data.get('content')

        logging.info("Retrieving ID Counter")
        id_counter = get_id_counter()
        if id_counter.current_highest_mind_message == None:
            id_counter.current_highest_mind_message = 0


        logging.info("Getting current ID")
        curID = id_counter.current_highest_mind_message
        curID = curID + 1

        logging.info("Creating MindMessage object")
        message = MindMessage(id=curID, name=sent_name, email=sent_email, product=sent_product, price=sent_price, content=sent_content)

        logging.info("Saving MindMessage object")
        message.put()


        id_counter.current_highest_mind_message = curID
        id_counter.put()

        logging.info("Returning response")
        self.response.headers.add_header('Access-Control-Allow-Origin', '*')
        return True