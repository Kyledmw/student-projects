import webapp2
from google.appengine.api import users

from jinja import TEMPLATE_ENVIRONMENT

templates_path = 'templates/'


class IndexPage(webapp2.RequestHandler):
    def get(self):
        template = TEMPLATE_ENVIRONMENT.get_template(templates_path + 'index.html')
        self.response.out.write(template.render())


class AdminPage(webapp2.RequestHandler):
    def get(self):
        user = users.get_current_user()
        if user:
            if users.is_current_user_admin():
                template = TEMPLATE_ENVIRONMENT.get_template(templates_path + 'admin.html')
                self.response.out.write(template.render())
            else:
                self.redirect('/')
        else:
            self.redirect(users.create_login_url(self.request.uri))