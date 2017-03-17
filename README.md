## Introduction

meta-htpc is an OpenEmbedded layer that builds some binaries and libraries necessary for kodi-based htpc platform.
Current release is 0.1

## Usage

To get correct templates for local.conf and bblayers.conf set the `TEMPLATECONF` variable. For example:
```
TEMPLATECONF=/path/to/meta-htpc/conf source oe-init-build-env /path/to/build/directory
```
Don't forget to remove old build/conf/templateconf.cfg at first.

