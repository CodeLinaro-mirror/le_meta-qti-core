SUMMARY = "Group to bring core utility packages"
LICENSE = "BSD-3-Clause"

inherit packagegroup

PROVIDES = "${PACKAGES}"

PACKAGES = ' \
    packagegroup-qti-core \
    packagegroup-qti-core-recovery \
'

RDEPENDS_${PN} = " \
    mtd-utils-ubifs \
    sec-config \
"
