#!/usr/bin/python

# Imports

from argparse import ArgumentParser
from os import makedirs
from os.path import exists, expanduser, join

import cherrypy

# Constants

COMODOO_OWN_DIR = join(expanduser('~'), '.comodoo')
COMODOO_SERVERURL_FILEPATH = join(COMODOO_OWN_DIR, 'server_url')

# Functions

def current_server_url():
    if ( not exists(COMODOO_SERVERURL_FILEPATH) ):
        return ''

    file = open(COMODOO_SERVERURL_FILEPATH, 'r')
    line = file.readline().replace('\n', '')
    file.close()

    return line

def save_server_url(server_url):
    if ( not exists(COMODOO_OWN_DIR) ):
        makedirs(COMODOO_OWN_DIR)

    file = open(COMODOO_SERVERURL_FILEPATH, 'w')
    file.write(server_url + '\n')
    file.close()

# Classes

class ServerUrl(object):
    @cherrypy.expose
    def index(self):
        return '''<html>
            <head></head>
            <body>
                <form method="get" action="save">
                    <input type="text" name="server_url" value="%s" />
                    <button type="submit">Use Server URL</button>
                </form>
            </body>
        <html>''' % (current_server_url())

    @cherrypy.expose
    def save(self, server_url):
        save_server_url(server_url)

        cherrypy.engine.exit()

# Main

if __name__ == '__main__':
    parser = ArgumentParser(description='Web server to save remote server URL')

    cherrypy.quickstart(ServerUrl())
