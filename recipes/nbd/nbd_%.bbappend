inherit update-alternatives

FILESEXTRAPATHS:prepend := "${THISDIR}/files:"

SRC_URI += "file://setup_nbdclient"
SRC_URI += "file://nbdtab"
SRC_URI += "file://nbdclient.service"

ALTERNATIVE:${PN}-client = "nbd-client"
ALTERNATIVE_TARGET[nbd-client] = "${sbindir}/nbd-client"
ALTERNATIVE_LINK_NAME[nbd-client] = "${sbindir}/nbd-client"
ALTERNATIVE_PRIORITY[nbd-client] = "100"

do_install:append() {
   if ${@bb.utils.contains('DISTRO_FEATURES', 'systemd', 'true', 'false', d)}; then
       # Install script that will setup the nbd-client
       install -d ${D}${sbindir}/
       install -m 0755 ${WORKDIR}/setup_nbdclient ${D}${sbindir}/setup_nbdclient
       #Install systemd service file
       install -d ${D}${systemd_unitdir}/system
       install -m 0644 ${WORKDIR}/nbdclient.service ${D}${systemd_unitdir}/system/

       #install nbdtab conf file
       install -d ${D}${sysconfdir}/
       install -m 0644 ${WORKDIR}/nbdtab ${D}${sysconfdir}/nbdtab
   fi
}

FILES:${PN}-client += "${sbindir}/*"
FILES:${PN}-client += "${systemd_unitdir}/system/*"
FILES:${PN}-client += "${sysconfdir}/*"