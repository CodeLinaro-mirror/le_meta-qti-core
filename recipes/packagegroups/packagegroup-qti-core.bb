SUMMARY = "Group to bring core utility packages"
LICENSE = "BSD-3-Clause"

inherit packagegroup

PROVIDES = "${PACKAGES}"

FLASHLESS_MEMORY = "${@d.getVar('MACHINE_SUPPORTS_FLASHLESS_MEMORY') or "False"}"

MTDUTILS ?= 'True'
MTDUTILS:cinder = 'False'

PACKAGES = ' \
    packagegroup-qti-core \
    packagegroup-qti-core-recovery \
'

RDEPENDS:${PN} = " \
    sec-config \
    ${@oe.utils.conditional('FLASHLESS_MEMORY', 'True', 'nbd-client', '', d)} \
    ${@oe.utils.conditional('MTDUTILS', 'True', 'mtd-utils-ubifs', '', d)} \
"
