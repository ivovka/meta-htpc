SUMMARY = "XBMC Video Disk Recorder XVDR PVR Add-on"
HOMEPAGE = "https://github.com/pipelka/xbmc-addon-xvdr"
LICENSE = "GPL-2.0"

LIC_FILES_CHKSUM = "file://COPYING;md5=892f569a555ba9c07a568a7c0c4fa63a"

SRC_URI = "git://github.com/pipelka/xbmc-addon-xvdr.git;branch=kodi-helix;protocol=http"
SRC_URI[md5sum] = "f6916524c302f3209fd0af507ab97387"
SRC_URI[sha256sum] = "c33c6431726378d5af575d8cfcacd34a50d17334e091dc4a095b6b75bc99b972"
SRCREV = "a8b46d5a2024dd7df6f701b484787c98381861f2"
PV = "kodi-helix+gitr${SRCPV}"
PR = "r0"

inherit autotools gettext

S = "${WORKDIR}/git"

EXTRA_OECONF = "--prefix=${libdir}/kodi"
#do_configure() {
#    sh bootstrap
#    oe_runconf
#}

PARALLEL_MAKE = ""

RDEPENDS_${PN} = "xbmc"
FILES_${PN}_append = " ${libdir}/kodi/*"

