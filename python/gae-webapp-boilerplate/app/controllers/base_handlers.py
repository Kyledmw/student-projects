import json
import webapp2


class RestHandler(webapp2.RequestHandler):
    def dispatch(self):
        super(RestHandler, self).dispatch()

    def send_json(self, r):
        self.response.headers['content-type'] = 'application/json'
        self.response.write(json.dumps(r))
