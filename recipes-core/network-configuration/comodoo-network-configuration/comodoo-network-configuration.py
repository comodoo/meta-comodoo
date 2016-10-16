#!/usr/bin/python

# Imports

from argparse import ArgumentParser
from os import getpgid, killpg, setsid
from signal import SIGTERM
from subprocess import Popen

# Constants

APP_ECA_WEB = '/opt/eca-web/start-eca-web'
APP_ECA_WEB_URL = '0.0.0.0:8080'

APP_KIOSK = '/opt/comodoo/bin/comodoo-kiosk.py'
APP_KIOSK_PARAM_URL = '--url'

# Main

def main():
    eca_web = Popen([APP_ECA_WEB])
    kiosk = Popen([APP_KIOSK, APP_KIOSK_PARAM_URL, APP_ECA_WEB_URL],
                  preexec_fn=setsid)

    eca_web.wait()
    killpg(getpgid(kiosk.pid), SIGTERM)

if __name__ == '__main__':
    parser = ArgumentParser(description='Configure Internet Connection')

    main()
