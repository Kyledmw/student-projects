
from google.appengine.ext import blobstore
from google.appengine.ext import webapp
from google.appengine.ext.webapp import blobstore_handlers

from UtilMethods import get_image_url, get_id_counter
from model import SingletonsController, AboutMe, ProfilePicture, Skill, \
    Experience


class AboutMeUploadHandler(webapp.RequestHandler):
    
    def post(self):
        try:
            aboutme = SingletonsController().getAboutMe()
            upText = self.request.get('text')
             
            if aboutme != None:                 
                aboutme.text = upText
                aboutme.put()
            else: 
                aboutme = AboutMe(text=upText)
                aboutme.put()
                
            self.redirect('/')
        except:
            self.error(404)
            
class ProfilePictureUploadHandler(blobstore_handlers.BlobstoreUploadHandler):
    
    def post(self):
        try:
            profile_pic = SingletonsController().getProfilePicture()
            upload = self.get_uploads()[0] 
            upImage = get_image_url(upload.key())
            
            if profile_pic != None:
                profile_pic.image = upImage
                blobstore.BlobInfo.get(profile_pic.blob_key).delete()
                profile_pic.blob_key = upload.key()
                profile_pic.put()
            else:
                profile_pic = ProfilePicture(image=upImage, blob_key=upload.key())
                profile_pic.put()
                
            self.redirect('/') 
        except:
            self.error(404)
            
class SkillUploadHandler(blobstore_handlers.BlobstoreUploadHandler):
    
    def post(self):
        try:
            id_counter = get_id_counter()
            curID = id_counter.current_highest_skill
            curID = curID + 1
            
            upload = self.get_uploads()[0] 
            upIcon = get_image_url(upload.key()) 
             
            upName = self.request.get('skill_name')
            upDesc = self.request.get('skill_desc')
            upProject = self.request.get('skill_project')
            upGithub = self.request.get('skill_github')
             
            skill = Skill(id=curID, 
                          name=upName, 
                          icon=upIcon, 
                          desc=upDesc, 
                          project=upProject, 
                          github=upGithub, 
                          blob_key=upload.key())
            skill.put()
            id_counter.current_highest_skill = curID
            id_counter.put()
            
            self.redirect('/') 
        except:
            self.error(404)
            
class ExperienceUploadHandler(blobstore_handlers.BlobstoreUploadHandler):
    
    def post(self):
        try:
            id_counter = get_id_counter()
            curID = id_counter.current_highest_experience
            curID = curID + 1
            
            upload = self.get_uploads()[0] 
            upImage = get_image_url(upload.key()) 
            
            upTitle = self.request.get('exp_title')
            upDesc = self.request.get('exp_desc')
            upWidth = self.request.get('exp_width')
            upHeight = self.request.get('exp_height')
            upLink = self.request.get('exp_link')
            
            experience = Experience(id=curID, 
                                    title=upTitle, 
                                    desc=upDesc, 
                                    image=upImage, 
                                    width=upWidth, 
                                    height=upHeight, 
                                    href=upLink,
                                    blob_key=upload.key())
            experience.put()
            
            id_counter.current_highest_experience = curID
            id_counter.put()
            
            self.redirect('/') 
        except:
            self.error(404)