SUMMARY = "Tools for managing memory technology devices"
HOMEPAGE = "http://www.linux-mtd.infradead.org/"
SECTION = "base"
LICENSE = "GPL-2.0-only"
LIC_FILES_CHKSUM = "file://${COREBASE}/meta/files/common-licenses/${LICENSE};md5=801f80980d171dd6425610833a22dbe6"

inherit autotools pkgconfig update-alternatives

DEPENDS = "zlib lzo e2fsprogs util-linux"
DEPENDS += "${@bb.utils.contains("DISTRO_FEATURES", "selinux", "libselinux", "", d)}"

PV = "2.0.0"

FILESEXTRAPATHS:prepend := "${WORKSPACE}/filesystems:"
SRC_URI = "file://mtd-utils"

S = "${UNPACKDIR}/mtd-utils"

# xattr support creates an additional compile-time dependency on acl because
# the sys/acl.h header is needed. libacl is not needed and thus enabling xattr
# regardless whether acl is enabled or disabled in the distro should be okay.
PACKAGECONFIG ?= "${@bb.utils.filter('DISTRO_FEATURES', 'xattr selinux', d)}"
PACKAGECONFIG[xattr] = ",,acl,"
PACKAGECONFIG[selinux] = "--with-selinux,--without-selinux,libselinux libselinux-native,"

EXTRA_OEMAKE = "'CC=${CC}' 'RANLIB=${RANLIB}' 'AR=${AR}' 'CFLAGS=${CFLAGS} ${@bb.utils.contains('PACKAGECONFIG', 'xattr', '', '-DWITHOUT_XATTR', d)} -I${S}/include -I${S}/ubi-utils/include -I${S}/tests/fs-tests/lib' 'BUILDDIR=${S}'"

do_compile:append () {
	oe_runmake tests
}

ubi_tests = " \
	integ \
	io_basic \
	io_paral \
	io_read \
	io_update \
	mkvol_bad \
	mkvol_basic \
	mkvol_paral \
	rsvol \
	volrefcnt \
	"

checkfs_tests = " \
	checkfs \
	makefiles \
	"

ALTERNATIVE:${PN} = "flash_eraseall"
ALTERNATIVE_LINK_NAME[flash_eraseall] = "${sbindir}/flash_eraseall"
# Use higher priority than busybox's flash_eraseall (created when built with CONFIG_FLASH_ERASEALL)
ALTERNATIVE_PRIORITY[flash_eraseall] = "100"

MTD_TEST_BIN_PATH = "${WORKSPACE}/filesystems/bin/target/mtd-utils"
do_install () {
	oe_runmake install DESTDIR=${D} SBINDIR=${sbindir} MANDIR=${mandir} INCLUDEDIR=${includedir}

	mkdir -p ${MTD_TEST_BIN_PATH}/fstests/
	find ${B}/tests/fs-tests/ -executable -type f -exec cp {} ${MTD_TEST_BIN_PATH}/fstests/ \;

	mkdir -p ${MTD_TEST_BIN_PATH}/ubi-tests/
	for test in ${ubi_tests}; do
		cp ${B}/$test ${MTD_TEST_BIN_PATH}/ubi-tests/
	done

	mkdir -p ${MTD_TEST_BIN_PATH}/checkfs/
	for test in ${checkfs_tests}; do
		cp ${B}/$test ${MTD_TEST_BIN_PATH}/checkfs/
	done
}

PACKAGES =+ "mtd-utils-jffs2 mtd-utils-ubifs mtd-utils-misc"

FILES:mtd-utils-jffs2 = "${sbindir}/mkfs.jffs2 ${sbindir}/jffs2dump ${sbindir}/jffs2reader ${sbindir}/sumtool"
FILES:mtd-utils-ubifs = "${sbindir}/mkfs.ubifs ${sbindir}/ubi* ${sbindir}/flash_erase ${sbindir}/nandwrite $(sbindir)/nanddump"
FILES:mtd-utils-misc = "${sbindir}/nftl* ${sbindir}/ftl* ${sbindir}/rfd* ${sbindir}/doc* ${sbindir}/serve_image ${sbindir}/recv_image"

BBCLASSEXTEND = "native"

# git/.compr.c.dep:46: warning: NUL character seen; rest of line ignored
# git/.compr.c.dep:47: *** missing separator.  Stop.
PARALLEL_MAKE = ""
