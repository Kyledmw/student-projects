from controllers.common_handlers import IndexPage, AdminPage

routes = [
    ('/', IndexPage),
    ('/admin', AdminPage)
]