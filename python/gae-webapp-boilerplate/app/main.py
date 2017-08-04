import webapp2
from routes import routes
from config import app_config


app = webapp2.WSGIApplication(routes, config=app_config, debug=app_config.get('debug', False))
