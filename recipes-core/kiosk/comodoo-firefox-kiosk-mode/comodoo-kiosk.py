#!/usr/bin/python

# Imports

from argparse import ArgumentParser
from distutils.dir_util import copy_tree
from fnmatch import fnmatch
from os import listdir
from os.path import exists, expanduser, join, relpath
from subprocess import call

# Constants

ARGUMENT_URL = 'url'
COMODOO_OWN_DIR = join(expanduser("~"), '.comodoo')
COMODOO_SHARED_DIR = '/usr/share/comodoo'

# Functions

def comodoo_shared_firefox_profile_dir():
    filenames = listdir(COMODOO_SHARED_DIR)
    filtered_filenames = [fn for fn in filenames if fnmatch(fn, 'firefox*')]
    if ( len(filtered_filenames) != 1 ):
        raise 'Shared Firefox directory not found'

    profile_dir = join(COMODOO_SHARED_DIR, filtered_filenames[0], 'profile')
    if ( not exists(profile_dir) ):
        raise 'Shared Firefox profile directory not found'

    return profile_dir

def comodoo_own_firefox_profile_dir():
    shared_firefox_profile_dir = comodoo_shared_firefox_profile_dir()
    relative_firefox_profile_dir = relpath(shared_firefox_profile_dir,
                                           COMODOO_SHARED_DIR)

    return join(COMODOO_OWN_DIR, relative_firefox_profile_dir)

# Main

def main(args):
    own_firefox_profile_dir = comodoo_own_firefox_profile_dir()
    if ( not exists(own_firefox_profile_dir) ):
        copy_tree(comodoo_shared_firefox_profile_dir(), own_firefox_profile_dir)

    firefox_args = ['firefox',
                    '--display=:0',
                    '--profile', own_firefox_profile_dir]
    if ( args[ARGUMENT_URL] ):
        firefox_args.append(args[ARGUMENT_URL])

    call(firefox_args)

if __name__ == '__main__':
    parser = ArgumentParser(description='Launch Firefox on kiosk mode')
    parser.add_argument('--' + ARGUMENT_URL, help='use URL to open browser')
    args = vars(parser.parse_args())

    main(args)
