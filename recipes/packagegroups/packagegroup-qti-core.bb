SUMMARY = "Group to bring Core Open Source Packages"

inherit packagegroup

PROVIDES = "${PACKAGES}"

PACKAGES = ' \
    packagegroup-qti-core \
'

RDEPENDS_${PN} = " \
    mtd-utils-ubifs \
    sec-config \
"
