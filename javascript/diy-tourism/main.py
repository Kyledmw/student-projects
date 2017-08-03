import ast
import json
import re

from google.appengine.api import mail
from google.appengine.ext import webapp
from google.appengine.ext.webapp.util import run_wsgi_app
import webapp2


class RestHandler(webapp2.RequestHandler):

    def dispatch(self):
        super(RestHandler, self).dispatch()

    def SendJson(self, r):
        self.response.headers['content-type'] = 'text/plain'
        self.response.write(json.dumps(r))
        
class CheckoutHandler(RestHandler):
    
    def post(self):
        data = ast.literal_eval(self.request.body)
        sent_name = data.get('name')
        sent_email = data.get('email')
        sent_content = data.get('content')
        
        return True

application = webapp.WSGIApplication([('/rest/checkout', CheckoutHandler)], debug=True)


def main():
    run_wsgi_app(application)

if __name__ == "__main__":
    main()