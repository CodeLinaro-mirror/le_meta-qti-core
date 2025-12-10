SUMMARY = "Group to bring core utility packages"
LICENSE = "BSD-3-Clause"

PACKAGE_ARCH = "${MACHINE_ARCH}"
inherit packagegroup

PROVIDES = "${PACKAGES}"

FLASHLESS_MEMORY = "${@d.getVar('MACHINE_SUPPORTS_FLASHLESS_MEMORY') or "False"}"

PACKAGES = ' \
    packagegroup-qti-core \
    packagegroup-qti-core-recovery \
'

RDEPENDS:${PN} = " \
    mtd-utils-ubifs \
    sec-config \
    ${@oe.utils.conditional('FLASHLESS_MEMORY', 'True', 'nbd-client', '', d)} \
"
