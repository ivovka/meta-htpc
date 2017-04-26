SUMMARY = "A command line Linux program which mounts and unmounts removable devices"
HOMEPAGE = "http://ignorantguru.github.io/udevil/"
LICENSE = "GPL-3.0"
LIC_FILES_CHKSUM = "file://COPYING;md5=d32239bcb673463ab874e80d47fae504"

SRC_URI = "https://github.com/IgnorantGuru/udevil/blob/pkg/${PV}/udevil-${PV}.tar.xz?raw=true;downloadfilename=udevil-${PV}.tar.xz \
           file://udevil-mount@.service \
           file://udevil-mount.rules \
	   file://0001-udevil-0.4.3-fix-compile-with-gcc6.patch \
          "
SRC_URI[md5sum] = "51b70af0d3d16575d41c910db3a2f099"
SRC_URI[sha256sum] = "446bbbd810d8379599f5aff3b94a19a617d7c84775b7f34a03c21f58835c3b76"

inherit autotools gettext pkgconfig systemd
DEPENDS = "glib-2.0 udev intltool-native"
#PACKAGECONFIG = "${@base_contains('DISTRO_FEATURES','systemd','systemd','',d)}"
#PACKAGECONFIG[systemd] = "--enable-systemd,--disable-systemd,systemd"
EXTRA_OECONF = " \
    --disable-systemd \
    "
SYSTEMD_SERVICE_${PN} = "udevil-mount@.service"
SYSTEMD_AUTO_ENABLE = "disable"

do_install_append () {
    rm -rf "${D}/${libdir}"
    rm -rf "${D}/${sysconfdir}/conf.d"
    rm -rf "${D}/${bindir}/devmon"
    install -D -m644 "${WORKDIR}/udevil-mount@.service" "${D}${systemd_unitdir}/system/udevil-mount@.service"
    install -D -m644 "${WORKDIR}/udevil-mount.rules" "${D}${sysconfdir}/udev/rules.d/udevil-mount.rules"
}
