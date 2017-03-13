SUMMARY = "Transmission is a cross-platform BitTorrent client"
HOMEPAGE = "http://www.transmissionbt.com/"
LICENSE = "GPL-2.0"

LIC_FILES_CHKSUM = "file://COPYING;md5=a1923fe8f8ff37c33665716af0ec84f1"
SRC_URI = "https://transmission.cachefly.net/transmission-${PV}.tar.xz \
    file://0001-replace-libsystemd-daemon-dependency.patch"

SRC_URI[md5sum] = "3fce404a436e3cd7fde80fb6ed61c264"
SRC_URI[sha256sum] = "3a8d045c306ad9acb7bf81126939b9594553a388482efa0ec1bfb67b22acd35f"

inherit autotools systemd useradd
DEPENDS = "zlib openssl curl libevent"
PACKAGECONFIG = "utp daemon ${@bb.utils.contains('DISTRO_FEATURES','systemd','systemd','',d)}"
PACKAGECONFIG[utp] = "--enable-utp,--disable-utp"
PACKAGECONFIG[daemon] = "--enable-daemon,--disable-daemon"
PACKAGECONFIG[systemd] = "--with-systemd-daemon,--without-systemd-daemon,systemd"
EXTRA_OECONF = " \
    HAVE_CXX=yes \
    --enable-lightweight \
    --without-gtk \
    --disable-nls \
    --disable-cli \
    "

SYSTEMD_SERVICE_${PN} = "transmission.service"
SYSTEMD_AUTO_ENABLE = "disable"

USERADD_PACKAGES = "${PN}"
USERADD_PARAM_${PN} = "-c 'Transmission BitTorrent Client' -u 169 -g transmission -b '/var/lib' -m -s /bin/false transmission"
GROUPADD_PARAM_${PN} = "-g 169 transmission"
do_install_append () {
    install -D -m644 "${S}/daemon/transmission-daemon.service" "${D}${systemd_unitdir}/system/transmission.service"
}
