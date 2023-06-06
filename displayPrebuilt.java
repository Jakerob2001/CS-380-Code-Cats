/**
 * Class for displaying prebuilt computers
 * @author Jake Robinson
 */
public class displayPrebuilt {


        /**
         * Prebuilt fields
         */
        private String comp_name;
        private displayPart motherboard;
        private displayPart gpu;
        private displayPart power_supply;
        private displayPart cpu;
        private displayPart ram;
        private displayPart aCase;
        private displayPart storage;
        private double price;

        /**
         * Function to return computer case
         * @return Part aCase
         */
        public displayPart getaCase() {
            return aCase;
        }

        /**
         * Function to return computer cpu
         * @return Part cpu
         */
        public displayPart getCpu() {
            return cpu;
        }

        /**
         * Function to return computer gpu
         * @return Part gpu
         */
        public displayPart getGpu() {
            return gpu;
        }

        /**
         * Function to return computer motherboard
         * @return Part motherboard
         */
        public displayPart getMotherboard() {
            return motherboard;
        }

        /**
         * Function to return computer power supply
         * @return Part power_supply
         */
        public displayPart getPower_supply() {
            return power_supply;
        }

        /**
         * Function to return computer ram
         * @return Part ram
         */
        public displayPart getRam() {
            return ram;
        }

        /**
         * Function to return computer storage
         * @return Part storage
         */
        public displayPart getStorage() {
            return storage;
        }

        /**
         * Function to return computer name
         * @return String comp_name
         */
        public String getComp_name() {
            return comp_name;
        }

        /**
         * Function to return computer price
         * @return double price
         */
        public double getPrice() {
            return price;
        }

        /**
         * Function to set computer case Part
         * @param aCase Part case to be used
         */
        public void setaCase(displayPart aCase) {
            this.aCase = aCase;
        }

        /**
         * Function to set computer name
         * @param comp_name Part case to be used
         */
        public void setComp_name(String comp_name) {
            this.comp_name = comp_name;
        }

        /**
         * Function to set computer cpu Part
         * @param cpu Part cpu to be used
         */
        public void setCpu(displayPart cpu) {
            this.cpu = cpu;
        }

        /**
         * Function to set computer gpu Part
         * @param gpu Part gpu to be used
         */
        public void setGpu(displayPart gpu) {
            this.gpu = gpu;
        }

        /**
         * Function to set computer motherboard Part
         * @param motherboard Part motherboard to be used
         */
        public void setMotherboard(displayPart motherboard) {
            this.motherboard = motherboard;
        }


        /**
         * Function to set computer power supply Part
         * @param power_supply Part power supply to be used
         */
        public void setPower_supply(displayPart power_supply) {
            this.power_supply = power_supply;
        }

        /**
         * Function to set computer ram Part
         * @param ram Part ram to be used
         */
        public void setRam(displayPart ram) {
            this.ram = ram;
        }

        /**
         * Function to set computer storage Part
         * @param storage Part storage to be used
         */
        public void setStorage(displayPart storage) {
            this.storage = storage;
        }

        /**
         * Function to set computer price
         * @param price double price to be used
         */
        public void setPrice(double price) {
            this.price = price;
        }

        /**
         * Prebuilt Constructor function
         * @param name Part
         * @param motherboard Part
         * @param gpu Part
         * @param ps Part
         * @param cpu Part
         * @param ram Part
         * @param aCase Part
         * @param storage Part
         */
        public displayPrebuilt(String name, displayPart motherboard, displayPart gpu, displayPart ps, displayPart cpu,
                        displayPart ram, displayPart aCase, displayPart storage, double price){
            setComp_name(name);
            setMotherboard(motherboard);
            setGpu(gpu);
            setPower_supply(ps);
            setCpu(cpu);
            setRam(ram);
            setaCase(aCase);
            setStorage(storage);
            setPrice(price);
        }

        public String toString(){
            return "Build: " + getComp_name() + "\n" +
                "Board: " + getMotherboard() + "\n" +
                "GPU: " + getGpu() + "\n" +
                "Power: " + getPower_supply() + "\n" +
                "CPU: " + getCpu() + "\n" +
                "Ram: " + getRam() + "\n" +
                "Case: " + getaCase() + "\n" +
                "Storage: " + getStorage() + "\n" +
                "Total Price: " + getPrice() + "\n";
    }
}
