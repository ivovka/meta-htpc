SUMMARY = "IPTV Simple PVR"
HOMEPAGE = "https://github.com/opdenkamp/xbmc-pvr-addons"
LICENSE = "GPL-2.0"

LIC_FILES_CHKSUM = "file://COPYING;md5=d32239bcb673463ab874e80d47fae504"

SRC_URI = "git://github.com/opdenkamp/xbmc-pvr-addons.git;branch=helix;protocol=http"
SRC_URI[md5sum] = "f6916524c302f3209fd0af507ab97387"
SRC_URI[sha256sum] = "c33c6431726378d5af575d8cfcacd34a50d17334e091dc4a095b6b75bc99b972"
SRCREV = "b19b0ee9473e501fc6e40ef7cb66ca95c7fdd3ad"
PV = "helix+gitr${SRCPV}"
PR = "r0"

inherit autotools

S = "${WORKDIR}/git"
EXTRA_OECONF = "--prefix=${libdir}/kodi"
EXTRA_OEMAKE = "-C ${B}/addons/pvr.iptvsimple"

RDEPENDS_${PN} = "xbmc"
do_install_append() {
    mkdir -p "${D}${libdir}"/kodi/addons
    mv "${D}${libdir}"/pvr.iptvsimple "${D}${libdir}"/kodi/addons/
    mkdir -p "${D}${datadir}"/kodi/addons
    mv "${D}${datadir}"/pvr.iptvsimple "${D}${datadir}"/kodi/addons/
    install -m 0644 "${B}"/addons/pvr.iptvsimple/addon/addon.xml "${D}${datadir}"/kodi/addons/pvr.iptvsimple/
}
FILES_${PN}_append = " ${libdir}/kodi/addons/pvr.iptvsimple/* ${datadir}/kodi/addons/pvr.iptvsimple/*"
FILES_${PN}-dbg_append = " ${libdir}/kodi/addons/pvr.iptvsimple/.debug"
