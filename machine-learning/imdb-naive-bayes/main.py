import sys
from runner.targets import MAP, run_all


def main(target):
    MAP[target]()


if __name__ == "__main__":
    if len(sys.argv) > 1:
        main(sys.argv[1])
    else:
        run_all()
