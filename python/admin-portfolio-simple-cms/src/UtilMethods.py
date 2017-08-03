import logging
from operator import itemgetter

from google.appengine.api import images

from model import SingletonsController, IDCounter, Skill, Experience, Message, MindMessage


def get_image_url(file_key):
    img_url = images.get_serving_url(file_key, size=None, crop=False, secure_url=None)
    return img_url

def get_id_counter():
    id_counter = SingletonsController().getIDCounter()
    
    if id_counter == None:
        id_counter = IDCounter(current_highest_skill=0, current_highest_experience=0, current_highest_message=0, current_highest_mind_message=0)
        id_counter.put()
    
    return id_counter

def get_skills():
    query = Skill.query()
    return query.fetch()

def get_experience():
    query = Experience.query()
    return query.fetch()

def get_skill(skill_id):
    query = Skill.query(Skill.id == skill_id)
    return query.fetch()[0]
    
def get_exp(exp_id):
    query = Experience.query(Experience.id == exp_id)
    return query.fetch()[0]

def get_message(message_id):
    query = Message.query(Message.id == message_id)
    return query.fetch()[0]

def get_messages():
    query = Message.query()
    return query.fetch()

def get_mind_messages():
    query = MindMessage.query()
    return query.fetch()

def get_skills_dict_list():
    dict_list = [skill_AsDict(skill) for skill in get_skills()]
    return sorted(dict_list, key=itemgetter('id'))

def get_experience_dict_list():
    dict_list = [exp_AsDict(exp) for exp in get_experience()]
    return sorted(dict_list, key=itemgetter('id'), reverse=True)

def get_aboutme_profilepic_dict():
    aboutme = SingletonsController().getAboutMe()
    profilepic = SingletonsController().getProfilePicture()
    
    return {'text' : aboutme.text, 
            'image' : profilepic.image
            }
    
def skill_AsDict(skill):
    return {'id' : skill.id, 
            'name' : skill.name, 
            'icon' : skill.icon, 
            'desc' : skill.desc, 
            'project' : skill.project, 
            'github' : skill.github
            }
    
def exp_AsDict(exp):
    return {'id' : exp.id,
            'title' : exp.title,
            'desc' : exp.desc, 
            'image' : exp.image,
            'width' : exp.width,
            'height' : exp.height,
            'href' : exp.href
            }
    
    