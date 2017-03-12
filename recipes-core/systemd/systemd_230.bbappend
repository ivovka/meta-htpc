FILESEXTRAPATHS_prepend := "${THISDIR}/files:"
SRC_URI_append = " file://enXXDHCP.network \
      "
PACKAGECONFIG_append = " networkd"

do_install_append() {
    install -d ${D}${sysconfdir}/systemd/network
    install -m 0644 ${WORKDIR}/enXXDHCP.network ${D}${sysconfdir}/systemd/network
}
