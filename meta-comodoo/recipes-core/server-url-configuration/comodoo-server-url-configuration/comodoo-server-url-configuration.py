#!/usr/bin/python

# Imports

from argparse import ArgumentParser
from os import getpgid, killpg, setsid
from os.path import join
from signal import SIGTERM
from subprocess import Popen

# Constants

COMODOO_DIR = '/opt/comodoo'
BIN_DIR = join(COMODOO_DIR, 'bin')

APP_WEB = join(BIN_DIR, 'comodoo-server-url-web.py')
APP_WEB_URL = '0.0.0.0:8080'

APP_KIOSK = join(BIN_DIR, 'comodoo-firefox-kiosk.py')
APP_KIOSK_PARAM_URL = '--url'

# Main

def main():
    web = Popen([APP_WEB])
    kiosk = Popen([APP_KIOSK, APP_KIOSK_PARAM_URL, APP_WEB_URL],
                  preexec_fn=setsid)

    web.wait()
    killpg(getpgid(kiosk.pid), SIGTERM)

if __name__ == '__main__':
    parser = ArgumentParser(description='Save remote server URL')

    main()
