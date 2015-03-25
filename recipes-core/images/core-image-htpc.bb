DESCRIPTION = "Image for htpc build. Must include X11 without window manager, \
vdr and xbmc"

IMAGE_FEATURES += "package-management"
IMAGE_LINGUAS = "en-us ru-ru"

#IMAGE_INSTALL = "packagegroup-core-boot ${ROOTFS_PKGMANAGE_BOOTSTRAP} ${CORE_IMAGE_EXTRA_INSTALL}"

LICENSE = "MIT"

inherit core-image

IMAGE_INSTALL_append = " packagegroup-mediacenter packagegroup-core-x11-xserver dropbear jfsutils xorg-launch-helper xserver-xorg-module-libwfb"
