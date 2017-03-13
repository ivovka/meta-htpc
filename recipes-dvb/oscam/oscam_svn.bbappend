FILESEXTRAPATHS_prepend := "${THISDIR}/files:"
SRC_URI = "svn://www.streamboard.tv/svn/oscam;module=trunk;protocol=http"
SRCREV = "11378"
SRC_URI_append = " file://oscam.service \
      "
inherit systemd

SYSTEMD_SERVICE_${PN} = "oscam.service"
SYSTEMD_AUTO_ENABLE_${PN} = "disable"

do_install_append() {
    install -d ${D}${systemd_unitdir}/system
    install -m 0644 ${WORKDIR}/oscam.service ${D}${systemd_unitdir}/system
    sed -i -e 's,@BINDIR@,${bindir},g' ${D}${systemd_unitdir}/system/oscam.service
    sed -i -e 's,@SYSCONFDIR@,${sysconfdir},g' ${D}${systemd_unitdir}/system/oscam.service
}
