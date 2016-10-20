#!/usr/bin/python

# Imports

from argparse import ArgumentParser
from os import getpgid, killpg, setsid
from os.path import join
from signal import SIGTERM
from subprocess import call, Popen

# Constants

ARGUMENT_URL = 'url'

COMODOO_DIR = '/opt/comodoo'
BIN_DIR = join(COMODOO_DIR, 'bin')
APP_NET_CONF = join(BIN_DIR, 'comodoo-network-configuration.py')
APP_HW_PROXY = join(BIN_DIR, 'comodoo-hardware-proxy.sh')
APP_KIOSK = join(BIN_DIR, 'comodoo-firefox-kiosk.py')
APP_KIOSK_ARG_URL = '--url'

# Functions

def configure_network():
    call([APP_NET_CONF])

def launch_pos(url):
    hw_proxy = Popen([APP_HW_PROXY], preexec_fn=setsid)

    kiosk_args = [APP_KIOSK]
    if ( url ):
        kiosk_args.extend([APP_KIOSK_ARG_URL, url])

    call(kiosk_args)

    killpg(getpgid(hw_proxy.pid), SIGTERM)

# Main

def main(args):
    configure_network()
    launch_pos(args[ARGUMENT_URL])

if __name__ == '__main__':
    parser = ArgumentParser(description='Launch Comodoo Point Of Sale')
    parser.add_argument('--' + ARGUMENT_URL, help='server URL')
    args = vars(parser.parse_args())

    main(args)
