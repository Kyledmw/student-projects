import os

from google.appengine.api import users
from google.appengine.ext import blobstore
from google.appengine.ext import webapp
from google.appengine.ext.webapp import template
from google.appengine.ext.webapp.util import run_wsgi_app

from DeleteHandlers import DeleteSkillHandler, DeleteExperienceHandler, \
    DeleteMessageHandler
from EditHandlers import EditSkillHandler, EditExperienceHandler
from RestHandlers import AboutMeHandler, SkillHandler, ExperienceHandler, \
    MessageHandler, MindsMessageHandler
from UploadHandlers import AboutMeUploadHandler, ProfilePictureUploadHandler, \
    SkillUploadHandler, ExperienceUploadHandler
from UtilMethods import get_skills, get_experience, get_messages, get_mind_messages
from model import SingletonsController


upload_profilepic_url = '/uploadProfilePicture'
upload_skill_url = '/uploadSkill'
upload_experience_url = '/uploadExperience'
upload_aboutme_url = '/uploadAbout'

edit_skill_url = '/editSkill'
edit_exp_url = '/editExperience'

delete_skill_url = '/deleteSkill'
delete_exp_url = '/deleteExperience'
delete_message_url = '/deleteMessage'

rest_aboutme = '/rest/aboutme'
rest_skills = '/rest/skills'
rest_experience = '/rest/experience'
rest_messages = '/rest/messages'
rest_mind_messages = '/rest/mindmessages'

class MainPage(webapp.RequestHandler):
    
    def get(self):
        user = users.get_current_user()
        logout_url = users.create_logout_url('/')
        
        blob_profilepic_up_url = blobstore.create_upload_url(upload_profilepic_url)
        blob_skill_up_url =  blobstore.create_upload_url(upload_skill_url)
        blob_exp_up_url = blobstore.create_upload_url(upload_experience_url)

        aboutme = SingletonsController().getAboutMe()
        profile_pic = SingletonsController().getProfilePicture()
        skills = get_skills()
        experience = get_experience()
        messages = get_messages()
        mind_messages = get_mind_messages();
        
        if user:
            if users.is_current_user_admin():
                try:
                    temp = os.path.join(os.path.dirname(__file__), 'templates/admin.html')
                    outstr = template.render(temp, {'logout_url' : logout_url, 
                                                    'up_aboutme_url' : upload_aboutme_url, 
                                                    'up_profilepic_url' :  blob_profilepic_up_url,
                                                    'up_skill_url' : blob_skill_up_url,
                                                    'up_experience_url' : blob_exp_up_url,
                                                    'edit_skill_url'  : edit_skill_url,
                                                    'edit_experience_url' : edit_exp_url,
                                                    'del_skill_url' : delete_skill_url,
                                                    'del_exp_url' : delete_exp_url,
                                                    'del_message_url' : delete_message_url,
                                                    'aboutme': aboutme,
                                                    'profilepic': profile_pic,
                                                    'skills' : skills,
                                                    'experience' : experience,
                                                    'messages' : messages,
                                                    'mind_messages': mind_messages
                                                    })
                    self.response.out.write(outstr)
                except:
                    self.error(404)
            else:
                temp = os.path.join(os.path.dirname(__file__), 'templates/index.html')
                outstr = template.render(temp, {'logout_url' : logout_url,
                                                'aboutme': aboutme,
                                                'profilepic': profile_pic,
                                                'skills' : skills,
                                                'experience' : experience
                                                    })
                self.response.out.write(outstr)
        else:
            self.redirect(users.create_login_url(self.request.uri))

application = webapp.WSGIApplication([('/', MainPage), 
                                      (upload_aboutme_url, AboutMeUploadHandler), 
                                      (upload_profilepic_url, ProfilePictureUploadHandler), 
                                      (upload_skill_url, SkillUploadHandler),
                                      (upload_experience_url, ExperienceUploadHandler),
                                      (edit_skill_url + '/([^/]+)?', EditSkillHandler),
                                      (edit_exp_url + '/([^/]+)?', EditExperienceHandler),
                                      (delete_skill_url + '/([^/]+)?', DeleteSkillHandler),
                                      (delete_exp_url + '/([^/]+)?', DeleteExperienceHandler),
                                      (delete_message_url + '/([^/]+)?', DeleteMessageHandler),
                                      (rest_aboutme, AboutMeHandler),
                                      (rest_skills, SkillHandler),
                                      (rest_experience, ExperienceHandler), 
                                      (rest_messages, MessageHandler),
                                      (rest_mind_messages, MindsMessageHandler)],
                                      debug=True)


def main():
    run_wsgi_app(application)

if __name__ == "__main__":
    main()