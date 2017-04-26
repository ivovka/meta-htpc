SUMMARY = "VDR - The Video Disk Recorder"
HOMEPAGE = "http://www.tvdr.de/"
LICENSE = "GPL-2.0"

LIC_FILES_CHKSUM = "file://COPYING;md5=892f569a555ba9c07a568a7c0c4fa63a"

SRC_URI = "ftp://ftp.tvdr.de/vdr/vdr-${PV}.tar.bz2 \
  file://vdr.service \
  "
SRC_URI[md5sum] = "8853f64c0fc3d41ffd3b4bfc6f0a14b7"
SRC_URI[sha256sum] = "7c259e1ed1f39d93d23df1d5d0f85dd2a1fa9ec1dadff79e5833e2ff3ebf6c4e"

S = "${WORKDIR}/${PN}-${PV}"

DEPENDS = "fontconfig freetype gettext-native libcap jpeg ncurses"

inherit pkgconfig systemd

EXTRA_OEMAKE = "'CONFDIR=/etc/vdr' 'VIDEODIR=${datadir}/vdr/video'"

do_install () {
  oe_runmake install DESTDIR=${D} PREFIX="/usr" CONFDIR="/etc/vdr" VIDEODIR="${datadir}/vdr/video"
  sed -i 's/-fdebug-prefix-map[^ ]*//g; s#${STAGING_DIR_TARGET}##g' ${D}${libdir}/pkgconfig/*.pc
  if ${@bb.utils.contains('DISTRO_FEATURES','systemd','true','false',d)}; then
    install -d ${D}${systemd_unitdir}/system
    install -m 0644 ${WORKDIR}/vdr.service ${D}${systemd_unitdir}/system/vdr.service
  fi
}

PACKAGES_DYNAMIC += "^vdr-plugin-.*"

#TODO: Add locale files to corresponding package
python populate_packages_prepend () {
    vdr_libdir = d.expand('${libdir}/vdr')
    do_split_packages(d, vdr_libdir, '^libvdr-(.*)\.so\.${PV}$', 'vdr-plugin-%s', 'VDR Plugin %s', extra_depends='', prepend=True)
}

CONFFILES_${PN} += "${sysconfdir}/vdr/channels.conf \
  ${sysconfdir}/vdr/diseqc.conf \
  ${sysconfdir}/vdr/keymacros.conf \
  ${sysconfdir}/vdr/scr.conf \
  ${sysconfdir}/vdr/sources.conf \
  ${sysconfdir}/vdr/svdrphosts.conf \
  "

RDEPENDS_${PN} += " glibc-charmap-iso-8859-5 glibc-gconv-iso8859-5"
SYSTEMD_SERVICE_${PN} = "vdr.service"

