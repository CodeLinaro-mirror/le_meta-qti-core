SUMMARY = "Group to bring Core Open Source Packages"
LICENSE = "BSD-3-Clause"

inherit packagegroup

PROVIDES = "${PACKAGES}"

PACKAGES = ' \
    packagegroup-qti-core \
'

RDEPENDS_${PN} = " \
    mtd-utils \
    sec-config \
"