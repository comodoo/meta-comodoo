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
LIB_DIR = join(COMODOO_DIR, 'lib')
WIZARD_DIR = join(LIB_DIR, 'pos-wizard')

APP_HW_PROXY = join(BIN_DIR, 'comodoo-hardware-proxy.sh')
APP_KIOSK = join(BIN_DIR, 'comodoo-firefox-kiosk.py')
APP_KIOSK_ARG_URL = '--url'
APP_WIZARD = join(WIZARD_DIR, 'comodoo_pos_wizard.py')
APP_WIZARD_URL = '127.0.0.1:8080'

# Functions
# Main

def main():
    wizard = Popen([APP_WIZARD])

    kiosk = Popen([APP_KIOSK, APP_KIOSK_ARG_URL, APP_WIZARD_URL])

    wizard.wait()
    hw_proxy = Popen([APP_HW_PROXY], preexec_fn=setsid)

    kiosk.wait()

    killpg(getpgid(hw_proxy.pid), SIGTERM)

if __name__ == '__main__':
    parser = ArgumentParser(description='Launch Comodoo Point Of Sale')

    main()
