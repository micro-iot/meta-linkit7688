EXTRA_CFLAGS += -Wno-error

obj-m := gpio-button-hotplug.o

SRC := $(shell pwd)

clean:
	@rm *.o || true 

all:
	$(MAKE) -C $(KERNEL_SRC) M=$(SRC)

modules_install:
	$(MAKE) -C $(KERNEL_SRC) M=$(SRC) modules_install
